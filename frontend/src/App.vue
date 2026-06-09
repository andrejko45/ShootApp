<script setup>
import Sidebar from './components/Sidebar.vue'
</script>

<template>
  <div class="layout">
    <Sidebar />

    <div class="main-area">
      <main class="content">
        <router-view />
      </main>

      <footer class="footer">
        ShootApp v1.0 |
        Andrej Várszegi |
        Anton Olos |
        Dávid Kamenský
      </footer>
    </div>
  </div>
</template>

<style scoped>
.layout {
  display: flex;
}

.main-area {
  flex: 1;
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

.content {
  flex: 1;
  padding: 30px;
  background: #f8fafc;
}

.footer {
  text-align: center;
  padding: 15px;
  background: white;
  border-top: 1px solid #e5e7eb;
  color: #6b7280;
  font-size: 14px;
}
</style>
