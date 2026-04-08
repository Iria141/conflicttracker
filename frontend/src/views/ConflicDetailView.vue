<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useLanguageStore } from '../stores/lenguajeStore.js'

const route = useRoute()
const router = useRouter()
const conflict = ref(null)
const lenguajeStore = useLanguageStore()

onMounted(async () => {
  const id = route.params.id

  const response = await fetch(`http://localhost:8080/api/v1/conflicts/${id}`)
  const data = await response.json()

  conflict.value = data
})

const getStatusClass = (estado) => {
  switch (estado) {
    case 'ACTIVO':
      return 'activo'
    case 'CONGELADO':
      return 'congelado'
    case 'FINALIZADO':
      return 'finalizado'
    default:
      return ''
  }
}

const formatDate = (fecha) => {
  if (!fecha) return 'Sin fecha'

  const date = new Date(fecha)
  return date.toLocaleDateString('es-ES', {
    day: '2-digit',
    month: 'long',
    year: 'numeric'
  })
}

const translateStatus = (estado) => {
  switch (estado) {
    case 'ACTIVO':
      return lenguajeStore.t.active
    case 'CONGELADO':
      return lenguajeStore.t.frozen
    case 'FINALIZADO':
      return lenguajeStore.t.finished
    default:
      return estado
  }
}
</script>

<template>
  <div class="container">
    <div class="top-bar">
      <div></div>

      <select v-model="lenguajeStore.currentLanguage" class="language-select">
        <option value="es">ES</option>
        <option value="en">EN</option>
        <option value="ca">CA</option>
      </select>
    </div>

    <div v-if="conflict" class="detail-card">
      <h1>{{ conflict.nombre }}</h1>

      <span :class="['status', getStatusClass(conflict.estado)]">
        {{ translateStatus(conflict.estado) }}
      </span>

      <p class="description">
        {{ conflict.descripcion }}
      </p>

      <div class="info">
        <div class="info-item">
          <span class="label">📅 {{ lenguajeStore.t.startDate }}</span>
          <span>{{ formatDate(conflict.fechaInicio) }}</span>
        </div>

        <div class="info-item">
          <span class="label">🆔 ID</span>
          <span>{{ conflict.id }}</span>
        </div>
      </div>
    </div>

    <p v-else>{{ lenguajeStore.t.detailsLoading }}</p>

    <button class="back-button" @click="router.push('/')">
      ← {{ lenguajeStore.t.back }}
    </button>
  </div>
</template>

<style>
.container {
  max-width: 700px;
  margin: auto;
  padding: 24px;
  font-family: Arial, sans-serif;
}

.back-button {
  margin-bottom: 18px;
  padding: 10px 16px;
  border: none;
  border-radius: 10px;
  background-color: #222;
  color: white;
  cursor: pointer;
  font-size: 0.95rem;
  transition: all 0.2s ease;
}

.back-button:hover {
  background-color: #000;
  transform: translateY(-1px);
}

.detail-card {
  border-radius: 18px;
  padding: 24px;
  background: #ffffff;
  box-shadow: 0 8px 24px rgba(0, 0, 0, 0.12);
  color: #222;
}

.detail-card h1 {
  margin: 0 0 12px;
  font-size: 2rem;
  color: #222;
}

.status {
  display: inline-block;
  padding: 6px 14px;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: bold;
  margin-bottom: 18px;
}

.description {
  margin: 18px 0;
  line-height: 1.6;
  color: #444;
  font-size: 1rem;
}

.info {
  margin-top: 20px;
  border-top: 1px solid #eee;
  padding-top: 10px;
}

.info-item {
  display: flex;
  justify-content: space-between;
  gap: 16px;
  padding: 12px 0;
  border-bottom: 1px solid #eee;
  color: #333;
}

.label {
  font-weight: bold;
  opacity: 0.8;
}

/* estados */
.activo {
  background-color: #f8d7da;
  color: #721c24;
}

.congelado {
  background-color: #fff3cd;
  color: #856404;
}

.finalizado {
  background-color: #d4edda;
  color: #155724;
}

.top-bar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.language-select {
  padding: 6px 10px;
  border-radius: 8px;
  border: 1px solid #ccc;
  cursor: pointer;
}

</style>