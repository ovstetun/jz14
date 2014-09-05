# Deploy mysql and mysql-service
--- update with password in mysql.json
cluster/kubecfg.sh -c ../jz14/kubernetes/mysql.json create pods
cluster/kubecfg.sh -c ../jz14/kubernetes/mysql-service.json create services

# create schema an user...
create schema ingredients;
grant all on ingredients.* to 'ingredients'@'%' identified by '<PASSWORD>';

# Deploy elastic and elastic-services
cluster/kubecfg.sh -c ../jz14/kubernetes/elastic.json create pods
cluster/kubecfg.sh -c ../jz14/kubernetes/elastic-http-service.json create services
cluster/kubecfg.sh -c ../jz14/kubernetes/elastic-native-service.json create services


# Deploy ingredients and ingredientsservice
--- update with password in ingredients-controllers.json
cluster/kubecfg.sh -c ../jz14/kubernetes/ingredients-controllers.json create replicationControllers
cluster/kubecfg.sh -c ../jz14/kubernetes/ingredients-service.json create services


# Deploy nginx and nginxservice
cluster/kubecfg.sh -c ../jz14/kubernetes/nginx-controllers.json create replicationControllers
cluster/kubecfg.sh -c ../jz14/kubernetes/nginx-service.json create services
