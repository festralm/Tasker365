<template>
  <ProfileLayout>
    <template v-slot:content>
      <a-modal v-model:visible="visibleTask" title="Создать рабочее пространство" @cancel="closeTask" @ok="closeTask">
        <a-input v-model:value="entityToCreate.name" placeholder="Название" style="margin-bottom: 20px" />


        <a-list
            item-layout="horizontal"
            :data-source="users"
            style="margin-bottom: 30px"
        >
          <template #renderItem="{ item }">
            <a-list-item>
              <template #actions>
                <a @click="addUser(item.id)">{{this.entityToCreate.users.includes(item.id) ? "юзер добавлен" : "добавить юзера"}}</a>
              </template>
              <a-skeleton avatar :title="false" :loading="!!item.loading" active>
                <a-list-item-meta
                    :description="item.email"
                >
                  <template #title>
                    <a href="https://www.antdv.com/">{{ item.name }}</a>
                  </template>
                  <template #avatar>
                    <a-avatar :src="item.avatarUrl" />
                  </template>
                </a-list-item-meta>
              </a-skeleton>
            </a-list-item>
          </template>
        </a-list>


        <a-button @click="createWorkspace" type="primary">Создать</a-button>
      </a-modal>

      <div style="width: 100%; display: flex; justify-content: right">
        <a-button @click="openTask" style="margin-bottom: 50px" type="primary" size="large" ghost>Создать
          рабочее
          пространство
        </a-button>
      </div>

      <div>
        <a-list :grid="{ gutter: 30, column: 3 }" :data-source="data">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card @click="goToWorkspace(item.id)" hoverable style="width: 330px">
                <template #cover>
                  <img
                      alt="example"
                      src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                  />
                </template>
                <a-card-meta :title="item.name">
                </a-card-meta>
                <template #actions>
                  <p>Количество досок: {{ item.boardCount }}</p>
                  <p>Количество юзеров: {{ item.userCount }}</p>
                </template>
              </a-card>
            </a-list-item>
          </template>
        </a-list>
      </div>

    </template>
  </ProfileLayout>
</template>

<script>
import ProfileLayout from "@/layouts/ProfileLayout";
import {errorNotification, successNotification} from "@/lib/notification";

export default {
  name: "DashboardPage",
  components: {ProfileLayout},
  data() {
    return {
      visibleTask: false,
      entityToCreate: {users: []}
    }
  },
  methods: {
    closeTask: function() {
      this.visibleTask = false;
      this.entityToCreate = {users: []};
    },

    openTask: function() {
      this.visibleTask = true;
    },

    addUser: function(id) {
      this.entityToCreate.users.push(id);
    },

    goToWorkspace: function(id) {
      this.$router.push('/user/workspace/' + id);
    },

    createWorkspace: async function() {
      try {
        await this.$store.dispatch('createWorkspace', this.entityToCreate);
        successNotification(this, "Пространство создано успешно");
        this.closeTask();
        this.$store.dispatch('loadWorkspaces');
      } catch (e) {
        errorNotification(this, e.message);
      }
    }
  },
  computed: {
    data() {
      return this.$store.getters.workspaces;
    },

    users() {
      return this.$store.getters.users;
    }
  },
  async beforeCreate() {
    await this.$store.dispatch('loadWorkspaces');
    await this.$store.dispatch('loadUsers');
  }
}
</script>

<style scoped>

</style>