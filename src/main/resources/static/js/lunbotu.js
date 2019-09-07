			var nowimg=0;
			var timer=null;
			var w = $('.box').width();
			var h = $('.box').height();
			$('.img img').css('width',w);
			$('.img img').css('height',h);
			
			console.log(w);
			console.log(h);
			$(".img li:first").clone().appendTo(".img");	
			$.each($(".img li"),function (index,element) {
				$(".img li").eq(index).css('left',index*w);
			});
			//左按钮
			$("#prev").click(function(){
				if(!$(".img").is(':animated')){
					if(nowimg>0){
						nowimg--
						$(".img").animate({"left":nowimg*-w},1000)
					}else{
						nowimg=5
						$(".img").css("left",nowimg*-w)
						nowimg--
						$(".img").animate({"left":nowimg*-w},1000)
					}
					$(".circle span").eq(nowimg).addClass('current').siblings().removeClass('current')
				}
			});
			//右按钮
			$("#next").click(nextFunc)
			
			function nextFunc(){
				if(!$(".img").is(':animated')){
					if(nowimg<5){
						nowimg++
						$(".img").animate({"left":nowimg*-w},1000)
					}else{
						nowimg=0
						$(".img").css("left",0)
						nowimg++
						$(".img").animate({"left":nowimg*-w},1000)
					}
					if(nowimg==5){
						$(".circle span").eq(0).addClass('current').siblings().removeClass('current')
					}else{
						$(".circle span").eq(nowimg).addClass('current').siblings().removeClass('current')
					}
				}
			};
			//自动轮播
			timer=setInterval(nextFunc,2000)
			$(".box").mouseenter(function(){
				clearInterval(timer)
			})
			$(".box").mouseout(function(){
				clearInterval(timer)
				timer=setInterval(nextFunc,2000)
			})
			
			// 小圆点
			$(".circle span").click(function(){
				nowimg=$(this).index()
				$(".circle span").eq(nowimg).addClass('current').siblings().removeClass('current')
				$(".img").animate({"left":nowimg*-w}, 1000)
				});