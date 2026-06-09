<template>
  <aside class="sidebar">
    <h2 class="logo">🎯 ShootApp</h2>

    <p class="subtitle">
      Manažment výsledkov v streleckej súťaži
    </p>

    <nav class="menu">
      <RouterLink to="/users">👤 Používatelia</RouterLink>
      <RouterLink to="/competitions">🏆 Súťaže</RouterLink>
      <RouterLink to="/roles">🎖️ Role</RouterLink>
      <RouterLink to="/participations">📝 Registrácie</RouterLink>
      <RouterLink to="/rounds">🎯 Kolá</RouterLink>
      <RouterLink to="/results">📊 Výsledky</RouterLink>
    </nav>
  </aside>
</template>

<style scoped>
.sidebar {
  width: 260px;
  min-height: 100vh;
  background: #1f2937;
  color: white;
  padding: 20px;
}

.logo {
  margin-bottom: 8px;
}

.subtitle {
  color: #9ca3af;
  font-size: 12px;
  margin-bottom: 30px;
  line-height: 1.4;
}

.menu {
  display: flex;
  flex-direction: column;
  gap: 12px;
}

a {
  color: white;
  text-decoration: none;
  padding: 12px;
  border-radius: 6px;
}

a:hover {
  background: #374151;
}

.router-link-active {
  background: #2563eb;
}
</style>
