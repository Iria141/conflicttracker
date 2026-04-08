import { defineStore } from 'pinia'
import { ref, computed } from 'vue'

export const useConflictStore = defineStore('conflicts', () => {
  const conflicts = ref([])
  const loading = ref(false)
  const error = ref(null)
  const search = ref('')
  const statusFilter = ref('TODOS')

const filteredConflicts = computed(() => {
  return conflicts.value.filter(conflict => {
    const matchSearch = conflict.nombre
      .toLowerCase()
      .includes(search.value.toLowerCase())

    const matchStatus =
      statusFilter.value === 'TODOS' ||
      conflict.estado === statusFilter.value

    return matchSearch && matchStatus
  })
})

  const fetchConflicts = async () => {
    loading.value = true
    error.value = null

    try {
      const response = await fetch('http://localhost:8080/api/v1/conflicts')

      if (!response.ok) {
        throw new Error('Error al obtener los conflictos')
      }

      const data = await response.json()
      conflicts.value = data
    } catch (err) {
      error.value = 'Error al cargar conflictos'
      console.error(err)
    } finally {
      loading.value = false
    }
  }

  const deleteConflict = (id) => {
    conflicts.value = conflicts.value.filter(conflict => conflict.id !== id)
  }

  const stats = computed(() => {
  return {
    total: conflicts.value.length,
    activo: conflicts.value.filter(c => c.estado === 'ACTIVO').length,
    congelado: conflicts.value.filter(c => c.estado === 'CONGELADO').length,
    finalizado: conflicts.value.filter(c => c.estado === 'FINALIZADO').length
  }
})

  return {
    conflicts,
    loading,
    error,
    search,
    filteredConflicts,
    fetchConflicts,
    deleteConflict,
    stats,
    statusFilter
  }
})

