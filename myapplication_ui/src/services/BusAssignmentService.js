import http from "../http-common"; 

class BusAssignmentService {
  getAllBusAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/busAssignment/busAssignments`, searchDTO);
  }

  get(busAssignmentId) {
    return this.getRequest(`/busAssignment/${busAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/busAssignment?field=${matchData}`, null);
  }

  addBusAssignment(data) {
    return http.post("/busAssignment/addBusAssignment", data);
  }

  update(data) {
  	return http.post("/busAssignment/updateBusAssignment", data);
  }
  
  uploadImage(data,busAssignmentId) {
  	return http.postForm("/busAssignment/uploadImage/"+busAssignmentId, data);
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

export default new BusAssignmentService();
