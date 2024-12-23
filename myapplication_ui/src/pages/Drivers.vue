<template>
  <div class="content">
    <div class="row">
      <div class="col-12">
        <card class="card-plain">
          <!-- <template slot="header">
            <h4 class="card-title">Table on Plain Background</h4>
            <p class="category">Here is a subtitle for this table</p>
          </template>-->
          <div class="table-full-width text-left">
            <driver-table
            v-if="drivers && drivers.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:drivers="drivers"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-drivers="getAllDrivers"
             >

            </driver-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DriverTable from "@/components/DriverTable";
import DriverService from "../services/DriverService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DriverTable,
  },
  data() {
    return {
      drivers: [],
	  totalElements: 0,
      page: 0,
      searchQuery: '',     
      table1: {
        title: "Simple Table",
        columns: [...tableColumns],
        data: [...tableData],
      },
    };
  },
  methods: {
    async getAllDrivers(sortBy='driverId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DriverService.getAllDrivers(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.drivers.length) {
					this.drivers = response.data.drivers;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching drivers:", error);
        }
        
      } catch (error) {
        console.error("Error fetching driver details:", error);
      }
    },
  },
  mounted() {
    this.getAllDrivers();
  },
  created() {
    this.$root.$on('searchQueryForDriversChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDrivers();
    })
  }
};
</script>
<style></style>
