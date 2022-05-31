<template>
  <DefaultLayout>
    <template v-slot:content>
      <div class="dark-container">
        <div class="dark-auth-form-container">
          <h3 class="dark-auth-form-title">
            {{title}}
          </h3>
          <a-form>
            <a-form-item class="dark-auth-form-item">
              <a-input
                  class="auth-input"
                  type="text"
                  placeholder="Email"
                  v-model="email"
              >
              </a-input>
            </a-form-item>
            <a-form-item class="dark-auth-form-item">
              <a-input
                  class="auth-input"
                  type="password"
                  placeholder="Пароль"
                  v-model="password"
              >
                <a-icon type="lock" style="color: rgba(0,0,0,.25)"/>
              </a-input>
            </a-form-item>
            <a-button type="primary" block class="dark-auth-form-submit" html-type="submit" @click="doLogin">
              Войти
            </a-button>
          </a-form>
        </div>
      </div>
    </template>
  </DefaultLayout>
</template>

<script>
import DefaultLayout from "@/layouts/DefaultLayout";
import {errorNotification, successNotification} from "@/lib/notification";


export default {
  name: "LoginPage",
  components: {DefaultLayout},
  data() {
    return {
      title: 'Вход Tasker365',
      email: '',
      password: ''
    }
  },
  methods: {
    async doLogin() {
      try {
        await this.$store.dispatch('login', {email: this.email, password: this.password});
        this.$router.push('/user/dashboard');
        successNotification(this, "Авторизация прошла успешно");
      } catch (e) {
        errorNotification(this, e.message);
      }
    }
  }
}
</script>

<style scoped>
.dark-container {
  min-height: 100vh;
}

.dark-auth-form-container {
  width: 520px;
  max-width: 100%;
  padding: 160px 35px 0;
  margin: 0 auto;
}

.dark-auth-form-title {
  font-size: 32px;
  line-height: 1.5em;
  color: #001529;
  margin: 0 auto 40px auto;
  text-align: center;
  font-weight: 700;
}
</style>