<template>
  <ProfileLayout>
    <template v-slot:content>
      <a-modal v-model:visible="visibleTask" title="Создать доску задач" @ok="closeTask">
        <a-input v-model:value="entityToCreate.name" placeholder="Название" style="margin-bottom: 20px" />
        <a-button type="primary" @click="createBoard">Создать</a-button>
      </a-modal>

      <div style="width: 100%; display: flex; justify-content: right">
        <a-button style="margin-bottom: 50px" type="primary" size="large" ghost>Добавить пользователя
        </a-button>
      </div>
      <div>
        <a-list :grid="{ gutter: 30, column: 6 }" :data-source="users">
          <template #renderItem="{ item }">
            <a-list-item style="display: flex; flex-direction: column; align-items: center">
              <a-avatar :size="64">
                <template #icon><UserOutlined /></template>
              </a-avatar>
              <p> {{ item.name }} </p>
            </a-list-item>
          </template>
        </a-list>
      </div>

      <div style="width: 100%; display: flex; justify-content: right">
        <a-button @click="openTask" style="margin-bottom: 50px" type="primary" size="large" ghost>Создать
          доску задач
        </a-button>
      </div>
      <div>
        <a-list :grid="{ gutter: 30, column: 3 }" :data-source="data">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card @click="goToBoard(item.id)" hoverable style="width: 330px">
                <template #cover>
                  <img
                      alt="example"
                      src="https://gw.alipayobjects.com/zos/rmsportal/JiqGstEfoWAOHiTxclqi.png"
                  />
                </template>
                <a-card-meta :title="item.name">
                </a-card-meta>
                <template #actions>
                  <p>Количество задач: {{ item.taskCount }}</p>
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
import { UserOutlined } from '@ant-design/icons-vue';
import {errorNotification, successNotification} from "@/lib/notification";


export default {
  name: "WorkspacePage",
  components: {ProfileLayout, UserOutlined},
  data() {
    return {
      visibleTask: false,
      entityToCreate: {workspaceId: ''}
    }
  },

  computed: {
    users() {
      return this.$store.getters.workspaceData.users;
    },
    // Boards
    data() {
      return this.$store.getters.workspaceData.boards;
    }
  },

  props: {
    workspaceId: {},
  },
  methods: {
    closeTask: function() {
      this.visibleTask = false;
    },
    openTask: function() {
      this.visibleTask = true;
    },

    goToBoard: function(id) {
      this.$router.push('/user/board/' + id);
    },

    createBoard: async function() {
      try {
        this.entityToCreate.workspaceId = this.workspaceId;
        await this.$store.dispatch('createBoard', this.entityToCreate);
        successNotification(this, "Доска создана успешно");
        this.closeTask();
        this.$store.dispatch('loadWorkspaceData', this.workspaceId);
      } catch (e) {
        errorNotification(this, e.message);
      }
    }
  },
  beforeCreate() {
    this.$store.dispatch('loadWorkspaceData', this.workspaceId);
  }
}
</script>

<style scoped>

</style>