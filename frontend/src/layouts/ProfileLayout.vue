<template>
  <a-layout class="layout">
    <a-layout-header>
      <div class="logo"/>
      <router-link to="/user/dashboard">
        <a-avatar :size="48" :style="{ float: 'right', marginTop: '8px' }">
          <template #icon>
            <UserOutlined/>
          </template>
        </a-avatar>
      </router-link>
    </a-layout-header>
    <a-layout-content style="padding: 50px 50px; background: #fff; min-height: 80vh">
      <slot name="content" />
    </a-layout-content>
    <a-layout-footer style="text-align: center">
      Tasker365 ©2022 copyright
    </a-layout-footer>
  </a-layout>
</template>

<script>
import { UserOutlined } from '@ant-design/icons-vue';


export default {
  name: 'ProfileLayout',
  components: {
    UserOutlined,
  },
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