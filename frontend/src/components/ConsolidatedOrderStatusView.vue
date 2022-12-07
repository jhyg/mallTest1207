
<template>

    <v-data-table
        :headers="headers"
        :items="consolidatedOrderStatus"
        :items-per-page="5"
        class="elevation-1"
    ></v-data-table>

</template>

<script>
    const axios = require('axios').default;

    export default {
        name: 'ConsolidatedOrderStatusView',
        props: {
            value: Object,
            editMode: Boolean,
            isNew: Boolean
        },
        data: () => ({
            headers: [
                { text: "id", value: "id" },
            ],
            consolidatedOrderStatus : [],
        }),
          async created() {
            var temp = await axios.get(axios.fixUrl('/consolidatedOrderStatuses'))

            temp.data._embedded.consolidatedOrderStatuses.map(obj => obj.id=obj._links.self.href.split("/")[obj._links.self.href.split("/").length - 1])

            this.consolidatedOrderStatus = temp.data._embedded.consolidatedOrderStatuses;
        },
        methods: {
        }
    }
</script>

