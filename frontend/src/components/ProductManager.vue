<template>
  <div class="card product-card">
    <div class="card-header">
      <div class="icon-circle">🔨</div>
      <h3>Engenharia de Produto</h3>
    </div>
    
    <form @submit.prevent="saveProduct" class="pro-form">
      <div class="input-group">
        <label>Informações Básicas</label>
        <div class="form-row">
          <input v-model="form.code" placeholder="Cód. Ref" class="st-input" required />
          <input v-model="form.name" placeholder="Nome do Produto" class="st-input" required />
          <input v-model.number="form.value" type="number" step="0.01" placeholder="Preço (R$)" class="st-input" required />
        </div>
      </div>

      <div class="recipe-section">
        <label>Receita / Composição</label>
        <div v-for="(item, index) in form.compositions" :key="index" class="recipe-row">
          <select v-model="item.rawMaterial.id" class="st-select" required>
            <option disabled value="">Insumo...</option>
            <option v-for="m in materials" :key="m.id" :value="m.id">
              {{ m.name }} (Estoque: {{ m.stockQuantity }})
            </option>
          </select>
          <input v-model.number="item.quantityNeeded" type="number" step="0.1" placeholder="Qtd" class="st-input qty" required />
          <button type="button" @click="removeMaterial(index)" class="btn-icon-delete">×</button>
        </div>
        
        <button type="button" @click="addMaterial" class="btn-outline-add">
          + Adicionar Componente
        </button>
      </div>

      <button type="submit" class="btn-primary-lg" :disabled="loading">
        {{ loading ? 'Processando...' : 'Finalizar e Salvar Produto' }}
      </button>
    </form>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from '../services/api';

const materials = ref([]);
const loading = ref(false);
const form = ref({
  code: '',
  name: '',
  value: null,
  compositions: []
});

const loadMaterials = async () => {
  try {
    const response = await api.getRawMaterials();
    materials.value = response.data;
  } catch (e) { console.error("Erro ao carregar insumos", e); }
};

const addMaterial = () => {
  form.value.compositions.push({ rawMaterial: { id: '' }, quantityNeeded: null });
};

const removeMaterial = (index) => {
  form.value.compositions.splice(index, 1);
};

const saveProduct = async () => {
  if (form.value.compositions.length === 0) {
    alert("Defina a composição do produto!");
    return;
  }
  
  loading.value = true;
  try {
    // Debug: Veja no console o que está sendo enviado
    console.log("Enviando dados:", JSON.stringify(form.value));
    
    await api.saveProduct(form.value);
    alert("✅ Produto registrado no ecossistema!");
    form.value = { code: '', name: '', value: null, compositions: [] };
  } catch (error) {
    console.error(error);
    alert("❌ Erro ao salvar. Verifique o console (F12).");
  } finally {
    loading.value = false;
  }
};

onMounted(loadMaterials);
</script>

<style scoped>
/* Cores Modernas */
:root { --primary: #2563eb; --bg: #ffffff; --text: #1e293b; }

.product-card { background: white; border-radius: 16px; border: 1px solid #e2e8f0; overflow: hidden; }
.card-header { background: #f8fafc; padding: 20px; display: flex; align-items: center; gap: 15px; border-bottom: 1px solid #e2e8f0; }
.icon-circle { background: #dbeafe; padding: 10px; border-radius: 50%; font-size: 1.2rem; }
.card-header h3 { margin: 0; color: #0f172a; font-size: 1.25rem; }

.pro-form { padding: 25px; }
.input-group label, .recipe-section label { display: block; font-weight: 600; color: #64748b; font-size: 0.85rem; text-transform: uppercase; margin-bottom: 10px; }

.form-row { display: grid; grid-template-columns: 1fr 2fr 1fr; gap: 12px; margin-bottom: 25px; }

.st-input, .st-select { 
  width: 100%; padding: 12px; border: 1.5px solid #e2e8f0; border-radius: 8px; 
  font-size: 0.95rem; transition: all 0.2s; 
}
.st-input:focus { border-color: #2563eb; outline: none; box-shadow: 0 0 0 3px rgba(37,99,235,0.1); }

.recipe-row { display: grid; grid-template-columns: 2fr 1fr 40px; gap: 10px; margin-bottom: 10px; align-items: center; }

.btn-outline-add { 
  width: 100%; background: none; border: 2px dashed #cbd5e1; color: #64748b; 
  padding: 10px; border-radius: 8px; cursor: pointer; font-weight: 500; margin-top: 5px;
}
.btn-outline-add:hover { background: #f8fafc; border-color: #94a3b8; color: #475569; }

.btn-primary-lg { 
  width: 100%; background: #2563eb; color: white; border: none; padding: 16px; 
  border-radius: 10px; font-size: 1rem; font-weight: 600; cursor: pointer; margin-top: 30px;
  box-shadow: 0 4px 6px -1px rgba(37,99,235,0.2); transition: transform 0.1s;
}
.btn-primary-lg:hover { background: #1d4ed8; transform: translateY(-1px); }
.btn-primary-lg:active { transform: translateY(0); }
.btn-primary-lg:disabled { background: #94a3b8; cursor: not-allowed; }

.btn-icon-delete { background: #fee2e2; color: #ef4444; border: none; width: 35px; height: 35px; border-radius: 8px; cursor: pointer; font-size: 1.2rem; }
</style>