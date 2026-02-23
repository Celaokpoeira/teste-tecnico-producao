// frontend/src/services/api.js
import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api'
});

export default {
  // Matérias-primas
  getRawMaterials: () => api.get('/raw-materials'),
  saveRawMaterial: (data) => api.post('/raw-materials', data),
  
  // Produtos
  getProducts: () => api.get('/products'),
  saveProduct: (data) => api.post('/products', data), // <-- Confira se é /products

  // Otimização
  calculateProduction: () => api.get('/production/calculate')
};