<script setup>
</script>

<template>
  <div class="base-card">
    <slot />
  </div>
</template>

<style>
.base-card {
  border-radius: 5px;
  padding: 5px;
  margin: 10px 0;
  background: #ffffff;
  box-shadow: 0 6px 16px rgba(0,0,0,0.1);
}
</style>