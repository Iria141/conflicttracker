import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { mensajes } from '../i18n/mensajes'

export const useLanguageStore = defineStore('language', () => {
  const currentLanguage = ref('es')

  const t = computed(() => mensajes[currentLanguage.value])

  const setLanguage = (lang) => {
    currentLanguage.value = lang
  }

  return {
    currentLanguage,
    t,
    setLanguage
  }
})