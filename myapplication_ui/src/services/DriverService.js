import http from "../http-common"; 

class DriverService {
  getAllDrivers(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/driver/drivers`, searchDTO);
  }

  get(driverId) {
    return this.getRequest(`/driver/${driverId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/driver?field=${matchData}`, null);
  }

  addDriver(data) {
    return http.post("/driver/addDriver", data);
  }

  update(data) {
  	return http.post("/driver/updateDriver", data);
  }
  
  uploadImage(data,driverId) {
  	return http.postForm("/driver/uploadImage/"+driverId, data);
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

export default new DriverService();
