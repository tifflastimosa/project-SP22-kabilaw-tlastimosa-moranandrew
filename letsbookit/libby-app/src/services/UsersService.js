import axios from "axios";

// connects us to the Users endpoint
const USERS_API_BASE_URL = "http://localhost:8080/users"

class UserService {

    // POST


    // GET

    // get all users
    getUsers() {
        return axios.get(USERS_API_BASE_URL);
    }

    // get user by id
    getUserById(userID) {
        return axios.get(USERS_API_BASE_URL + '/' + userID)
    }

    // PUT


    // DELETE

    deleteUser(userID) {
        return axios.delete(USERS_API_BASE_URL + '/delete/' + userID)
    }



}

// export so that we can import into different js files
export default new UserService()