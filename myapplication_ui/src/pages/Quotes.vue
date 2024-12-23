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
            <quote-table
            v-if="quotes && quotes.length > 0"
				:title="table1.title"
				:sub-title="table1.subTitle"
				:quotes="quotes"
				:totalElements="totalElements"
				:page="page"
				:columns="table1.columns"
 				@get-all-quotes="getAllQuotes"
             >

            </quote-table>
          </div>
        </card>
      </div>

    </div>
  </div>
</template>
<script>
import { Card } from "@/components/Card";

import QuoteTable from "@/components/QuoteTable";
import QuoteService from "../services/QuoteService";

const tableColumns = [];
const tableData = [
];

export default {
  components: {
    Card,
    QuoteTable,
  },
  data() {
    return {
      quotes: [],
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
    async getAllQuotes(sortBy='quoteId',sortOrder='true',page="0",size="50") {
      try {
        try {
			const searchDTO = {
				sortBy: sortBy, 
				sortOrder: sortOrder, 
				searchQuery: this.searchQuery,
				page: page, 
				size: size
			};
	        let response = await QuoteService.getAllQuotes(searchDTO);
	        if (!response.data.empty) {
		        if (response.data.quotes.length) {
					this.quotes = response.data.quotes;
				}
      			this.totalElements = response.data.totalElements;
      			this.page = response.data.page;
	        }
        } catch (error) {
          console.error("Error fetching quotes:", error);
        }
        
      } catch (error) {
        console.error("Error fetching quote details:", error);
      }
    },
  },
  mounted() {
    this.getAllQuotes();
  },
  created() {
    this.$root.$on('searchQueryForQuotesChanged', (searchQuery) => {
    	this.searchQuery = searchQuery;
    	this.getAllQuotes();
    })
  }
};
</script>
<style></style>
