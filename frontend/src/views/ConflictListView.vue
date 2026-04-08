<script setup>
import { onMounted } from 'vue'
import { useConflictStore } from '../stores/conflictStore.js'
import ConflictCard from '../components/ConflictCard.vue'
import { useLanguageStore } from '@/stores/lenguajeStore.js'

const conflictStore = useConflictStore()
const lenguajeStore = useLanguageStore()

onMounted(() => {
  conflictStore.fetchConflicts()
})
</script>

<template>
  <div class="container">

    <!-- selector idioma -->
    <div class="top-bar">
      <div></div>
      <select v-model="lenguajeStore.currentLanguage" class="language-select">
        <option value="es">🇪🇸 ES</option>
        <option value="en">🇬🇧 EN</option>
        <option value="ca">🇦🇩 CA</option>
      </select>
    </div>

    <!-- título -->
    <h1>🌍 {{ lenguajeStore.t.title }}</h1>

    <!-- estadísticas -->
    <div class="stats">
      <span>🌍 {{ lenguajeStore.t.total }}: {{ conflictStore.stats.total }}</span>
      <span>🔴 {{ lenguajeStore.t.active }}: {{ conflictStore.stats.activo }}</span>
      <span>🟡 {{ lenguajeStore.t.frozen }}: {{ conflictStore.stats.congelado }}</span>
      <span>🟢 {{ lenguajeStore.t.finished }}: {{ conflictStore.stats.finalizado }}</span>
    </div>

    <!-- filtro -->
    <select v-model="conflictStore.statusFilter" class="filter">
      <option value="TODOS">{{ lenguajeStore.t.all }}</option>
      <option value="ACTIVO">{{ lenguajeStore.t.active }}</option>
      <option value="CONGELADO">{{ lenguajeStore.t.frozen }}</option>
      <option value="FINALIZADO">{{ lenguajeStore.t.finished }}</option>
    </select>

    <!-- buscador -->
    <input
      v-model="conflictStore.search"
      :placeholder="lenguajeStore.t.searchPlaceholder"
      class="search"
    />

    <!-- estados -->
    <p v-if="conflictStore.loading">{{ lenguajeStore.t.loading }}</p>

    <p v-else-if="conflictStore.error">
      {{ conflictStore.error }}
    </p>

    <p v-else-if="conflictStore.filteredConflicts.length === 0">
      {{ lenguajeStore.t.noResults }}
    </p>

    <!-- lista -->
    <ConflictCard
      v-for="conflict in conflictStore.filteredConflicts"
      :key="conflict.id"
      :conflict="conflict"
      @deleteConflict="conflictStore.deleteConflict"
    />
  </div>
</template>

<style>
.container {
  max-width: 700px;
  margin: auto;
  padding: 20px;
  font-family: Arial, sans-serif;
}

.search {
  width: 100%;
  padding: 10px;
  margin-bottom: 15px;
  border-radius: 8px;
  border: 1px solid #ccc;
}

.stats {
  display: flex;
  gap: 10px;
  margin-bottom: 10px;
  flex-wrap: wrap;
}

.stats span {
  background: #222;
  color: white;
  padding: 5px 10px;
  border-radius: 8px;
  font-size: 0.85rem;
}

.filter {
  margin-bottom: 10px;
  padding: 8px;
  border-radius: 8px;
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