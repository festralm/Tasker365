<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo" />
      <a-menu
          theme="dark"
          mode="horizontal"
          :style="{ lineHeight: '64px', float: 'right' }"
      >
        <router-link to="register"><a-menu-item key="1">Регистрация</a-menu-item></router-link>
        <router-link to="login"><a-menu-item key="2">Вход</a-menu-item></router-link>
      </a-menu>
    </a-layout-header>
    <a-layout-content style="padding: 0 50px; background: #fff;">
      <slot name="content" />
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      Tasker365 ©2022 copyright
    </a-layout-footer>
  </a-layout>
</template>

<script>
export default {
  name: 'DefaultLayout',
}
</script>

<style>
.site-layout-content {
  min-height: 280px;
  padding: 24px;
  background: #fff;
}

.logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.site-layout-content {
  background: #141414;
}
</style>