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
                  v-model:value="email"
                  type="text"
                  placeholder="Email"
              >
              </a-input>
            </a-form-item>
            <a-form-item class="dark-auth-form-item">
              <a-input
                  class="auth-input"
                  v-model:value="name"
                  type="text"
                  placeholder="ФИО"
              >
              </a-input>
            </a-form-item>
            <a-form-item class="dark-auth-form-item">
              <a-input
                  class="auth-input"
                  v-model:value="password"
                  type="password"
                  placeholder="Пароль"
              >
              </a-input>
            </a-form-item>
            <a-button type="primary" block class="dark-auth-form-submit" html-type="submit" @click="doRegister">
              Регистрация
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
  name: "RegisterPage",
  components: {DefaultLayout},
  data() {
    return {
      title: 'Регистрация',
      email: '',
      password: '',
      name: ''
    }
  },
  methods: {
    async doRegister() {
      if (this.email === '' || this.password === '' || this.name === '') {
        return errorNotification(this, "Заполните данные");
      } else {
        try {
          await this.$store.dispatch('register', {
            email: this.email,
            password: this.password,
            name: this.name
          });
          this.$router.push('/login');
          successNotification(this, "Регистрация прошла успешно");
        } catch (e) {
          errorNotification(this, e.message);
        }
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