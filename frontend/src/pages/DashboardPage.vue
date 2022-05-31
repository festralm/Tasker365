<template>
  <ProfileLayout>
    <template v-slot:content>
      <a-modal v-model:visible="visibleTask" title="Создать рабочее пространство" @ok="closeTask">
        <a-input v-model:value="entityToCreate.name" placeholder="Название" style="margin-bottom: 20px" />
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
              <a-card hoverable style="width: 330px">
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
      entityToCreate: {}
    }
  },
  methods: {
    closeTask: function() {
      this.visibleTask = false;
    },

    openTask: function() {
      this.visibleTask = true;
    },

    createWorkspace: async function() {
      try {
        await this.$store.dispatch('createWorkspace', this.entityToCreate);
        successNotification(this, "Пространство создано успешно");
        this.closeTask();
      } catch (e) {
        errorNotification(this, e.message);
      }
    }
  },
  computed: {
    data() {
      return this.$store.getters.workspaces;
    }
  },
  async beforeCreate() {
    await this.$store.dispatch('loadWorkspaces');
  }
}
</script>

<style scoped>

</style>