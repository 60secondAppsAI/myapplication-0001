import http from "../http-common"; 

class AssignedToService {
  getAllAssignedTos(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/assignedTo/assignedTos`, searchDTO);
  }

  get(assignedToId) {
    return this.getRequest(`/assignedTo/${assignedToId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/assignedTo?field=${matchData}`, null);
  }

  addAssignedTo(data) {
    return http.post("/assignedTo/addAssignedTo", data);
  }

  update(data) {
  	return http.post("/assignedTo/updateAssignedTo", data);
  }
  
  uploadImage(data,assignedToId) {
  	return http.postForm("/assignedTo/uploadImage/"+assignedToId, data);
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

export default new AssignedToService();
