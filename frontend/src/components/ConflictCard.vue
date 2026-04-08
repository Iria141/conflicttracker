<script setup>
import { useRouter } from 'vue-router'
import BaseCard from './BaseCard.vue'
import { useLanguageStore } from '../stores/lenguajeStore.js'

const lenguajeStore = useLanguageStore()

defineProps({
  conflict: Object
})

const emit = defineEmits(['deleteConflict'])
const router = useRouter()

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

const getCountryCode = (nombre) => {
  if (nombre.includes('Ucrania')) return 'UA'
  if (nombre.includes('Sáhara')) return 'EH'
  if (nombre.includes('Balcanes')) return 'RS'
  if (nombre.includes('Fronteras')) return 'ES'

  return 'UN'
}

</script>

<template>

  <BaseCard>
    <div
      :class="getStatusClass(conflict.estado)"
      @click="router.push(`/conflicts/${conflict.id}`)"
    >
        <div class="header">
            <img 
                :src="`https://flagsapi.com/${getCountryCode(conflict.nombre)}/flat/32.png`" 
                alt="flag"
                class="flag"
            />
            <h3>{{ conflict.nombre }}</h3>
        </div>
      <p>{{ conflict.descripcion }}</p>
      <strong>{{ conflict.estado }}</strong>

      <br />

    <button @click.stop="emit('deleteConflict', conflict.id)">
    {{ lenguajeStore.t.delete }}
    </button>
    </div>
  </BaseCard>
</template>

<style>
.card {
  border-radius: 12px;
  padding: 16px;
  margin: 10px 0;
  transition: all 0.2s ease;
  cursor: pointer;
  box-shadow: 0 2px 6px rgba(0,0,0,0.1);
}

/* Hover efecto */
.card:hover {
  transform: translateY(-3px);
  box-shadow: 0 6px 14px rgba(0,0,0,0.15);
}

/* Título */
.card h3 {
  margin: 0 0 8px;
  font-size: 1.2rem;
}

/* Descripción */
.card p {
  margin: 0 0 10px;
  font-size: 0.95rem;
  opacity: 0.85;
}

/* Estado */
.card strong {
  display: inline-block;
  padding: 4px 10px;
  border-radius: 20px;
  font-size: 0.8rem;
}

/* Botón */
button {
  margin-top: 10px;
  padding: 6px 12px;
  border: none;
  border-radius: 6px;
  background-color: #333;
  color: white;
  cursor: pointer;
}

button:hover {
  background-color: #000;
}

.activo {
  background: linear-gradient(135deg, #f8d7da, #f1b0b7);
  color: #721c24;
}

.congelado {
  background: linear-gradient(135deg, #fff3cd, #ffe8a1);
  color: #856404;
}

.finalizado {
  background: linear-gradient(135deg, #d4edda, #a8e0b5);
  color: #155724;
}

.header {
  display: flex;
  align-items: center;
  gap: 10px;
}

.flag {
  width: 32px;
  height: 24px;
  object-fit: cover;
  box-shadow: 0 1px 3px rgba(0,0,0,0.2);
}

.header h3 {
  margin: 0;
}

</style>