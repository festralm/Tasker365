<template>
  <ProfileLayout>
    <template v-slot:content>
      <a-modal v-model:visible="visibleTask" :title="currentTask.title" @ok="closeTask">
        <p>{{currentTask.description}}</p>
      </a-modal>
      <a-modal v-model:visible="visibleCreate" title="Создать задачу" @ok="closeCreate">
        <a-input v-model:value="entityToCreate.title" placeholder="Название" style="margin-bottom: 20px" />
        <a-input v-model:value="entityToCreate.description" placeholder="Описание" style="margin-bottom: 20px" />
        <a-button type="primary" @click="createTask">Создать</a-button>
      </a-modal>

      <div style="display: flex; gap: 30px">
        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="dataTodo" style="min-width: 250px; max-width: 350px">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.title" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>{{item.description}}</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>Нужно сделать</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate('todo')" type="primary" shape="round" size="large">
                <template #icon>
                  <PlusOutlined/>
                </template>
              </a-button>
            </div>
          </template>
        </a-list>

        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="dataWork" style="min-width: 250px; max-width: 350px">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.title" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>{{item.description}}</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>В работе</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate('work')" type="primary" shape="round" size="large">
                <template #icon>
                  <PlusOutlined/>
                </template>
              </a-button>
            </div>
          </template>
        </a-list>

        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="dataDone" style="min-width: 250px; max-width: 350px">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.title" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>{{item.description}}</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>Готово</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate('done')" type="primary" shape="round" size="large">
                <template #icon>
                  <PlusOutlined/>
                </template>
              </a-button>
            </div>
          </template>
        </a-list>
      </div>

    </template>
  </ProfileLayout>
</template>

<script>
import ProfileLayout from "@/layouts/ProfileLayout";
import { PlusOutlined } from '@ant-design/icons-vue';
import {errorNotification, successNotification} from "@/lib/notification";


// STATUSES: todo, work, done
export default {
  name: "BoardPage",
  components: {ProfileLayout, PlusOutlined},
  data() {
    return {
      currentTask: {},
      visibleTask: false,
      entityToCreate: {},
      visibleCreate: false,
      currentStatus: 'todo'
    }
  },
  props: {
    boardId: {},
  },

  computed: {
    data() {
      return this.$store.getters.boardData.tasks;
    },

    dataTodo() {
      return this.$store.getters.todoData;
    },
    dataDone() {
      return this.$store.getters.doneData;
    },
    dataWork() {
      return this.$store.getters.workData;
    }
  },

  methods: {
    closeTask: function() {
      this.visibleTask = false;
    },
    openTask: function(item) {
      this.visibleTask = true;
      this.currentTask = item;
    },

    closeCreate: function() {
      this.visibleCreate = false;
    },
    openCreate: function(status) {
      this.currentStatus = status;
      this.visibleCreate = true;
      // send to Server new Task entityToCreate
    },

    createTask: async function () {
      try {
        this.entityToCreate.boardId = this.boardId;
        this.entityToCreate.priority = "default";
        this.entityToCreate.status = this.currentStatus;
        await this.$store.dispatch('createTask', this.entityToCreate);
        successNotification(this, "Задача создана успешно");
        this.closeCreate();
        this.$store.dispatch('loadBoardData', this.boardId);
      } catch (e) {
        errorNotification(this, e.message);
      }
    }
  },
  beforeCreate() {
    this.$store.dispatch('loadBoardData', this.boardId);
  }
}
</script>

<style scoped>

</style>