<template>
  <div class="app-layout">
    <header class="top-bar">
      <div class="top-bar-container">
        <div class="brand-identity">
          <div class="logo-box">
            <span class="logo-emoji">🏭</span>
          </div>
          <div class="brand-titles">
            <h1>Smart Factory <span class="accent">Manager</span></h1>
            <p class="signature">Desenvolvido por <strong>Marcelo Ferreira Bispo</strong></p>
          </div>
        </div>

        <div class="system-status">
          <div class="status-pill" :class="{ 'status-online': isOnline }">
            <span class="pulse-dot"></span>
            <span class="status-label">{{ isOnline ? 'Sistema Online' : 'Conectando...' }}</span>
          </div>
        </div>
      </div>
    </header>

    <main class="dashboard-wrapper">
      <div class="grid-layout">
        <section class="ops-column">
          <header class="section-header">
            <span class="icon">📁</span>
            <h2>Gestão de Dados</h2>
          </header>
          <div class="manager-stack">
            <RawMaterialManager />
            <div class="divider"></div>
            <ProductManager />
          </div>
        </section>

        <section class="intel-column">
          <header class="section-header">
            <span class="icon">📊</span>
            <h2>Otimização de Resultados</h2>
          </header>
          <ProductionOptimizer />
          
          <div class="guide-card">
            <div class="guide-header">
              <span class="light-icon">💡</span>
              <h3>Fluxo de Trabalho</h3>
            </div>
            <ol class="steps-list">
              <li>Cadastre os <strong>Insumos</strong> com o estoque atual.</li>
              <li>Defina a <strong>Receita</strong> dos produtos.</li>
              <li>Aperte o botão para encontrar o <strong>Lucro Máximo</strong>.</li>
            </ol>
          </div>
        </section>
      </div>
    </main>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import api from './services/api';
import RawMaterialManager from './components/RawMaterialManager.vue';
import ProductManager from './components/ProductManager.vue';
import ProductionOptimizer from './components/ProductionOptimizer.vue';

const isOnline = ref(false);

onMounted(async () => {
  try {
    await api.getRawMaterials();
    isOnline.value = true;
  } catch (e) {
    isOnline.value = false;
  }
});
</script>

<style>
/* Importação de fonte moderna */
@import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;600;800&display=swap');

:root {
  --brand-blue: #0f172a;
  --accent-blue: #3b82f6;
  --bg-main: #f8fafc;
  --text-main: #334155;
  --success: #10b981;
  --danger: #ef4444;
  --card-shadow: 0 4px 6px -1px rgb(0 0 0 / 0.1), 0 2px 4px -2px rgb(0 0 0 / 0.1);
}

body {
  margin: 0;
  background-color: var(--bg-main);
  color: var(--text-main);
  font-family: 'Inter', sans-serif;
  -webkit-font-smoothing: antialiased;
}

/* Navbar Estilizada */
.top-bar {
  background: rgba(255, 255, 255, 0.8);
  backdrop-filter: blur(12px);
  border-bottom: 1px solid #e2e8f0;
  padding: 1rem 0;
  position: sticky;
  top: 0;
  z-index: 1000;
}

.top-bar-container {
  max-width: 1400px;
  margin: 0 auto;
  padding: 0 2rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.brand-identity { display: flex; align-items: center; gap: 1rem; }

.logo-box {
  background: var(--brand-blue);
  width: 45px;
  height: 45px;
  border-radius: 12px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 1.5rem;
}

.brand-titles h1 {
  margin: 0;
  font-size: 1.2rem;
  font-weight: 800;
  color: var(--brand-blue);
  text-transform: uppercase;
  letter-spacing: -0.5px;
}

.brand-titles .accent { color: var(--accent-blue); }

.signature {
  margin: 0;
  font-size: 0.75rem;
  color: #64748b;
  letter-spacing: 0.5px;
}

/* Status Indicator com Pulsação */
.status-pill {
  display: flex;
  align-items: center;
  gap: 10px;
  padding: 6px 16px;
  background: #fee2e2;
  border-radius: 99px;
  border: 1px solid #fecaca;
  transition: all 0.3s ease;
}

.status-online {
  background: #dcfce7;
  border-color: #bbf7d0;
}

.pulse-dot {
  width: 8px;
  height: 8px;
  background: var(--danger);
  border-radius: 50%;
}

.status-online .pulse-dot {
  background: var(--success);
  box-shadow: 0 0 0 0 rgba(16, 185, 129, 1);
  animation: pulse 2s infinite;
}

@keyframes pulse {
  0% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(16, 185, 129, 0.7); }
  70% { transform: scale(1); box-shadow: 0 0 0 10px rgba(16, 185, 129, 0); }
  100% { transform: scale(0.95); box-shadow: 0 0 0 0 rgba(16, 185, 129, 0); }
}

.status-label { font-size: 0.8rem; font-weight: 600; color: #1e293b; }

/* Dashboard Layout */
.dashboard-wrapper {
  max-width: 1400px;
  margin: 2rem auto;
  padding: 0 2rem;
}

.grid-layout {
  display: grid;
  grid-template-columns: 1.1fr 0.9fr;
  gap: 2.5rem;
}

.section-header {
  display: flex;
  align-items: center;
  gap: 12px;
  margin-bottom: 1.5rem;
}

.section-header h2 {
  margin: 0;
  font-size: 1.1rem;
  color: #0f172a;
  font-weight: 700;
}

.manager-stack { display: flex; flex-direction: column; gap: 2rem; }

.guide-card {
  margin-top: 2.5rem;
  background: linear-gradient(to bottom right, #eff6ff, #dbeafe);
  padding: 1.5rem;
  border-radius: 16px;
  border: 1px solid #bfdbfe;
}

.guide-header { display: flex; align-items: center; gap: 10px; margin-bottom: 1rem; }
.guide-header h3 { margin: 0; font-size: 1rem; color: #1e40af; }

.steps-list {
  margin: 0;
  padding-left: 1.2rem;
  color: #1e3a8a;
  font-size: 0.9rem;
}

.steps-list li { margin-bottom: 8px; }

/* Sobrescrita de estilo dos componentes filhos */
.card {
  border: 1px solid #e2e8f0 !important;
  border-radius: 16px !important;
  box-shadow: var(--card-shadow) !important;
}

@media (max-width: 1100px) {
  .grid-layout { grid-template-columns: 1fr; }
}
</style>