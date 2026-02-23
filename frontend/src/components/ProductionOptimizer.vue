<template>
  <div class="card">
    <h3>🚀 Otimização de Lucro</h3>
    <p>Clique no botão para calcular a melhor estratégia de produção baseada no estoque atual.</p>
    <button @click="calculate" class="btn-calc" :disabled="loading">
      {{ loading ? 'Calculando...' : 'Calcular Produção Máxima' }}
    </button>

    <div v-if="result" class="result-area">
      <h4>Resultado da Sugestão:</h4>
      <ul>
        <li v-for="(qty, name) in result.suggested_production" :key="name">
          <strong>{{ name }}</strong>: {{ qty }} unidade(s)
        </li>
      </ul>
      <p class="total">Lucro Total Estimado: <span>R$ {{ result.total_value.toFixed(2) }}</span></p>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import api from '../services/api';

const result = ref(null);
const loading = ref(false);

const calculate = async () => {
  loading.value = true;
  try {
    const response = await api.calculateProduction();
    result.value = response.data;
  } catch (error) {
    alert("Erro ao calcular. Verifique se há produtos e insumos cadastrados.");
  } finally {
    loading.value = false;
  }
};
</script>