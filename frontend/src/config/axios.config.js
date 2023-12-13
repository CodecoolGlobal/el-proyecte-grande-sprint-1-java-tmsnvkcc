import axios from 'axios';

const axiosConfig = axios.create({
  timeout: 30000,
  withCredentials: true,
  headers: {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Credentials': true,
    'Access-Control-Allow-Headers': 'Origin, Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE',
  },
});

const token = window.localStorage.getItem('token');

const axiosConfigWithAuth = axios.create({
  timeout: 30000,
  withCredentials: true,
  headers: {
    'Authorization': `Bearer ${token}`,
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Access-Control-Allow-Credentials': true,
    'Access-Control-Allow-Headers': 'Origin, Content-Type',
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET, POST, PATCH, PUT, DELETE',
  },
});

export {
  axiosConfig,
  axiosConfigWithAuth
};
