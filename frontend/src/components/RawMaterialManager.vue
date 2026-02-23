<template>
  <div class="card">
    <h3>📦 Cadastro de Insumos (Matéria-Prima)</h3>
    <form @submit.prevent="saveMaterial" class="form-group">
      <input v-model="form.code" placeholder="Código (ex: ACO01)" required />
      <input v-model="form.name" placeholder="Nome do Material" required />
      <input v-model.number="form.stockQuantity" type="number" step="0.01" placeholder="Qtd em Estoque" required />
      <button type="submit" class="btn-save">Salvar Insumo</button>
    </form>

    <table class="table">
      <thead>
        <tr>
          <th>Código</th>
          <th>Nome</th>
          <th>Estoque</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="m in materials" :key="m.id">
          <td>{{ m.code }}</td>
          <td>{{ m.name }}</td>
          <td>{{ m.stockQuantity }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const materials = ref([]);
const form = ref({ code: '', name: '', stockQuantity: null });

const loadMaterials = async () => {
  const response = await api.getRawMaterials();
  materials.value = response.data;
};

const saveMaterial = async () => {
  await api.saveRawMaterial(form.value);
  form.value = { code: '', name: '', stockQuantity: null };
  loadMaterials();
};

onMounted(loadMaterials);
</script>