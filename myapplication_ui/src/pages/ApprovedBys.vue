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
            <approvedBy-table
            v-if="approvedBys && approvedBys.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:approvedBys="approvedBys"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-approved-bys="getAllApprovedBys"
             >

            </approvedBy-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import ApprovedByTable from "@/components/ApprovedByTable";
import ApprovedByService from "../services/ApprovedByService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    ApprovedByTable,
  },
  data() {
    return {
      approvedBys: [],
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
    async getAllApprovedBys(sortBy='approvedById',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await ApprovedByService.getAllApprovedBys(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.approvedBys.length) {
					this.approvedBys = response.data.approvedBys;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching approvedBys:", error);
        }
        
      } catch (error) {
        console.error("Error fetching approvedBy details:", error);
      }
    },
  },
  mounted() {
    this.getAllApprovedBys();
  },
  created() {
    this.$root.$on('searchQueryForApprovedBysChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllApprovedBys();
    })
  }
};
</script>
<style></style>
