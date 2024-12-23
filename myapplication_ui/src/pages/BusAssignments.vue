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
            <busAssignment-table
            v-if="busAssignments && busAssignments.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:busAssignments="busAssignments"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-bus-assignments="getAllBusAssignments"
             >

            </busAssignment-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import BusAssignmentTable from "@/components/BusAssignmentTable";
import BusAssignmentService from "../services/BusAssignmentService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    BusAssignmentTable,
  },
  data() {
    return {
      busAssignments: [],
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
    async getAllBusAssignments(sortBy='busAssignmentId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await BusAssignmentService.getAllBusAssignments(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.busAssignments.length) {
					this.busAssignments = response.data.busAssignments;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching busAssignments:", error);
        }
        
      } catch (error) {
        console.error("Error fetching busAssignment details:", error);
      }
    },
  },
  mounted() {
    this.getAllBusAssignments();
  },
  created() {
    this.$root.$on('searchQueryForBusAssignmentsChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllBusAssignments();
    })
  }
};
</script>
<style></style>
