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
            <assignedTo-table
            v-if="assignedTos && assignedTos.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:assignedTos="assignedTos"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-assigned-tos="getAllAssignedTos"
             >

            </assignedTo-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import AssignedToTable from "@/components/AssignedToTable";
import AssignedToService from "../services/AssignedToService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    AssignedToTable,
  },
  data() {
    return {
      assignedTos: [],
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
    async getAllAssignedTos(sortBy='assignedToId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await AssignedToService.getAllAssignedTos(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.assignedTos.length) {
					this.assignedTos = response.data.assignedTos;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching assignedTos:", error);
        }
        
      } catch (error) {
        console.error("Error fetching assignedTo details:", error);
      }
    },
  },
  mounted() {
    this.getAllAssignedTos();
  },
  created() {
    this.$root.$on('searchQueryForAssignedTosChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllAssignedTos();
    })
  }
};
</script>
<style></style>
