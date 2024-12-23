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
            <requestedBy-table
            v-if="requestedBys && requestedBys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:requestedBys="requestedBys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-requested-bys="getAllRequestedBys"
             >

            </requestedBy-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import RequestedByTable from "@/components/RequestedByTable";
import RequestedByService from "../services/RequestedByService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    RequestedByTable,
  },
  data() {
    return {
      requestedBys: [],
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
    async getAllRequestedBys(sortBy='requestedById',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await RequestedByService.getAllRequestedBys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.requestedBys.length) {
					this.requestedBys = response.data.requestedBys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching requestedBys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching requestedBy details:", error);
      }
    },
  },
  mounted() {
    this.getAllRequestedBys();
  },
  created() {
    this.$root.$on('searchQueryForRequestedBysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllRequestedBys();
    })
  }
};
</script>
<style></style>
