import http from "../http-common"; 

class ApprovedByService {
  getAllApprovedBys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/approvedBy/approvedBys`, searchDTO);
  }

  get(approvedById) {
    return this.getRequest(`/approvedBy/${approvedById}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/approvedBy?field=${matchData}`, null);
  }

  addApprovedBy(data) {
    return http.post("/approvedBy/addApprovedBy", data);
  }

  update(data) {
  	return http.post("/approvedBy/updateApprovedBy", data);
  }
  
  uploadImage(data,approvedById) {
  	return http.postForm("/approvedBy/uploadImage/"+approvedById, data);
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

export default new ApprovedByService();
