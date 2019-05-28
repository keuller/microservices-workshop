from locust import HttpLocust, TaskSet, task
import json

headers = { 
    "Content-Type": "application/json"
}

class CustomerTasks(TaskSet):
    # def on_start(self):
    #     self.client.post("/v1/customers", {
    #         "name": "test_user",
    #         "email": "test@test.com"
    #     })
    
    @task
    def index(self):
        self.client.get("/v1/customers")

    @task
    def update_customer(self):
        customer = {
            "name": "Abdoral Gusmao Santos", 
            "email": "abdoragusmao@gmail.com", 
            "birthday": "15/09/1989"
        }
        self.client.put("/v1/customers/0e7ab678-22b1-4a43-be97-811279eac10d", data=json.dumps(customer), headers=headers)

    @task
    def find_by_id(self):
        self.client.get("/v1/customers/0e7ab678-22b1-4a43-be97-811279eac10d")
        
class WebsiteUser(HttpLocust):
    task_set = CustomerTasks
    host = "http://localhost:8080"
    min_wait = 1500
    max_wait = 3000
