import http from "../http-common"; 

class DriverAssignmentService {
  getAllDriverAssignments(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/driverAssignment/driverAssignments`, searchDTO);
  }

  get(driverAssignmentId) {
    return this.getRequest(`/driverAssignment/${driverAssignmentId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/driverAssignment?field=${matchData}`, null);
  }

  addDriverAssignment(data) {
    return http.post("/driverAssignment/addDriverAssignment", data);
  }

  update(data) {
  	return http.post("/driverAssignment/updateDriverAssignment", data);
  }
  
  uploadImage(data,driverAssignmentId) {
  	return http.postForm("/driverAssignment/uploadImage/"+driverAssignmentId, data);
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

export default new DriverAssignmentService();
