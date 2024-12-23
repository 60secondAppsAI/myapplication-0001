import http from "../http-common"; 

class RequestedByService {
  getAllRequestedBys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/requestedBy/requestedBys`, searchDTO);
  }

  get(requestedById) {
    return this.getRequest(`/requestedBy/${requestedById}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/requestedBy?field=${matchData}`, null);
  }

  addRequestedBy(data) {
    return http.post("/requestedBy/addRequestedBy", data);
  }

  update(data) {
  	return http.post("/requestedBy/updateRequestedBy", data);
  }
  
  uploadImage(data,requestedById) {
  	return http.postForm("/requestedBy/uploadImage/"+requestedById, data);
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

export default new RequestedByService();
