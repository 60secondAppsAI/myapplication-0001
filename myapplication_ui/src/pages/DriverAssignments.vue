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
            <driverAssignment-table
            v-if="driverAssignments && driverAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:driverAssignments="driverAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-driver-assignments="getAllDriverAssignments"
             >

            </driverAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import DriverAssignmentTable from "@/components/DriverAssignmentTable";
import DriverAssignmentService from "../services/DriverAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    DriverAssignmentTable,
  },
  data() {
    return {
      driverAssignments: [],
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
    async getAllDriverAssignments(sortBy='driverAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await DriverAssignmentService.getAllDriverAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.driverAssignments.length) {
					this.driverAssignments = response.data.driverAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching driverAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching driverAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllDriverAssignments();
  },
  created() {
    this.$root.$on('searchQueryForDriverAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllDriverAssignments();
    })
  }
};
</script>
<style></style>
