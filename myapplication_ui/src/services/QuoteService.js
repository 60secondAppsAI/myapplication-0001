import http from "../http-common"; 

class QuoteService {
  getAllQuotes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/quote/quotes`, searchDTO);
  }

  get(quoteId) {
    return this.getRequest(`/quote/${quoteId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/quote?field=${matchData}`, null);
  }

  addQuote(data) {
    return http.post("/quote/addQuote", data);
  }

  update(data) {
  	return http.post("/quote/updateQuote", data);
  }
  
  uploadImage(data,quoteId) {
  	return http.postForm("/quote/uploadImage/"+quoteId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new QuoteService();
