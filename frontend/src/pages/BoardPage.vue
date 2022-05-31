<template>
  <ProfileLayout>
    <template v-slot:content>
      <a-modal v-model:visible="visibleTask" :title="currentTask.name" @ok="closeTask">
        <p>{{currentTask.description}}</p>
      </a-modal>
      <a-modal v-model:visible="visibleCreate" title="Создать задачу" @ok="closeCreate">
        <a-input v-model:value="entityToCreate.name" placeholder="Название" style="margin-bottom: 20px" />
        <a-input v-model:value="entityToCreate.description" placeholder="Описание" style="margin-bottom: 20px" />
        <a-button type="primary">Создать</a-button>
      </a-modal>

      <div style="display: flex">
        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="data">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.name" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>Описание задачи, которая находится в карточке</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>Нужно сделать</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate" type="primary" shape="round" size="large">
                <template #icon>
                  <PlusOutlined/>
                </template>
              </a-button>
            </div>
          </template>
        </a-list>

        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="data">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.name" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>Описание задачи, которая находится в карточке</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>В работе</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate" type="primary" shape="round" size="large">
                <template #icon>
                  <PlusOutlined/>
                </template>
              </a-button>
            </div>
          </template>
        </a-list>

        <a-list :grid="{ gutter: 30, column: 1 }" :data-source="data">
          <template #renderItem="{ item }">
            <a-list-item>
              <a-card :title="item.name" @click="openTask(item)" :hoverable="true" style="max-width: 320px; margin-top: 15px">
                <p>{{item.description}}</p>
              </a-card>
            </a-list-item>
          </template>
          <template #header>
            <h2>Готово</h2>
          </template>
          <template #footer>
            <div style="display: flex; justify-content: center; width: 80%">
              <a-button @click="openCreate" type="primary" shape="round" size="large">
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


export default {
  name: "BoardPage",
  components: {ProfileLayout, PlusOutlined},
  data() {
    return {
      data: [{name: 'Задача 1', description: 'Описание задачи, которая находится в карточке'}, {name: 'Задача 2', description: 'Описание задачи, которая находится в карточке'}],
      currentTask: {},
      visibleTask: false,
      entityToCreate: {},
      visibleCreate: false
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
    openCreate: function() {
      this.visibleCreate = true;
      // send to Server new Task entityToCreate
    },
  }
}
</script>

<style scoped>

</style>