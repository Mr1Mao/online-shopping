a = new Vue({
		el:"#head-box",
		data:{
			userinfo:[],
			isLogin:true,
			userCenterShow:false
		},
		created: function () {
			axios.get('/getUserInfo', {			//AJAX
			}).then((response) => {
				this.userinfo = response.data;
				if(this.userinfo.status == 1){
					this.isLogin=true;
				}else{
					this.isLogin=false;
				}
			}).catch(function (error) {
				console.log(error);
			});
		},methods:{
		}
	})