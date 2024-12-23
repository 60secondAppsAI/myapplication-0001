import http from "../http-common"; 

class BusService {
  getAllBuss(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/bus/buss`, searchDTO);
  }

  get(busId) {
    return this.getRequest(`/bus/${busId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/bus?field=${matchData}`, null);
  }

  addBus(data) {
    return http.post("/bus/addBus", data);
  }

  update(data) {
  	return http.post("/bus/updateBus", data);
  }
  
  uploadImage(data,busId) {
  	return http.postForm("/bus/uploadImage/"+busId, data);
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

export default new BusService();
