/*
 1. 鼠标移入显示,移出隐藏
 目标: 手机京东, 客户服务, 网站导航, 我的京东, 去购物车结算, 全部商品
 2. 鼠标移动切换二级导航菜单的切换显示和隐藏
 3. 输入搜索关键字, 列表显示匹配的结果
 4. 点击显示或者隐藏更多的分享图标
 5. 鼠标移入移出切换地址的显示隐藏
 6. 点击切换地址tab

 7. 鼠标移入移出切换显示迷你购物车
 8. 点击切换产品选项 (商品详情等显示出来)

 9. 点击向右/左, 移动当前展示商品的小图片
 10. 当鼠标悬停在某个小图上,在上方显示对应的中图
 11. 当鼠标在中图上移动时, 显示对应大图的附近部分区域
 */

/*
1. 对哪个/些元素绑定什么监听?
2. 对哪个/些元素进行什么DOM操作?
 */
$(function () {
  show_hide();
  hoverSubMenu();
  search();
  share();
  showAddress();
  clickTabs();
  showMinCart();
  clickProductTabs();
  moveMiniImg();
  hoverMiniImg();

  // 1. 鼠标移入显示,移出隐藏
  // 目标: 手机京东, 客户服务, 网站导航, 我的京东, 去购物车结算, 全部商品
  function show_hide() {
      $('[name=show_hide]').hover(function () {
          //显示
          var id = this.id+'_items';
          $('#'+id).show();
      },function () {
          //隐藏
          var id = this.id+'_items';
          $('#'+id).hide();
      });
  }

    // 2. 鼠标移动切换二级导航菜单的切换显示和隐藏
    function hoverSubMenu(){
      $('#category_items>div').hover(function () {
          $(this).children(':last').show();
    },function () {
        $(this).children(':last').hide();
    })
    }

    // 3. 输入搜索关键字, 列表显示匹配的结果
    function search() {
        $('#txtSearch').on('keyup focus',function () {
            //如果有文本才显示列表
            var txt = this.value.trim();
            if(txt){
                $('#search_helper').show();
            }
        }).blur(function () {
            $('#search_helper').hide();
        });
    }

    // 4. 点击显示或者隐藏更多的分享图标
    function share() {
        var isOpen = false;//当前状态(初始为关闭)
        var $shareMore = $('#shareMore');
        var $parent = $shareMore.parent();
        var $as = $shareMore.prevAll('a:lt(2)');
        var $b = $shareMore.children();
        $shareMore.click(function () {
            if(isOpen){//去关闭
                $parent.css('width',155);
                $as.hide();
                $b.removeClass('backword');
            }else{//打开
                $parent.css('width',200);
                $as.show();
                $b.addClass('backword');
            }
            isOpen = !isOpen;
        });
    }

    // 5. 鼠标移入移出切换地址的显示隐藏
    function showAddress() {
      var $select = $('#store_select');
        $select.hover(function () {
            $(this).children(':gt(0)').show();
        },function () {
            $(this).children(':gt(0)').hide();
        })
        .children(':last').click(function () {
            $select.children(':gt(0)').hide();
        });
    }

    // 6. 点击切换地址tab
    function clickTabs() {
      var $store_tabs_li = $('#store_tabs>li');
        $store_tabs_li.click(function () {
            $store_tabs_li.removeClass('hover');
            // $(this).addClass('hover');
            this.className = 'hover';
        });
    }

    // 7. 鼠标移入移出切换显示迷你购物车
    function showMinCart(){
      $('#minicart').hover(function () {
         this.className = 'minicart';
         $(this).children(':last').show();
      },function () {
          this.className = '';
          $(this).children(':last').hide();
      });
    }

    // 8. 点击切换产品选项 (商品详情等显示出来)
    function clickProductTabs(){
      var $lis = $('#product_detail>ul>li');
      var $contents = $('#product_detail>div:gt(0)');
      $lis.click(function () {
        $lis.removeClass('current');
        this.className = 'current';
        //隐藏所有的$contents
        $contents.hide();
        //显示当前contents
        var index = $(this).index();
        $contents.eq(index).show();
      });
    }

    // 9. 点击向右/左, 移动当前展示商品的小图片
    function moveMiniImg(){
      var $as = $('#preview>h1>a');
      var $backward = $as.first();
      var $forward = $as.last();
      var $ul = $('#icon_list');
      var SHOW_COUNT = 5;
      var imgCount = $ul.children('li').length;
      var moveCount = 0;//移动的次数(向右为正，向左为负)
      var liWidth = $ul.children(':first').width();
      //初始化更新
      if(imgCount>SHOW_COUNT){
          // $forward[0].className = 'forward';
          $forward.attr('class','forward');
      }

      $forward.click(function () {
          //判断是否需要移动,如果不需要直接结束
          if(moveCount === imgCount - SHOW_COUNT){
              return;
          }
          moveCount++;
          //更新向左的按钮
          $backward.attr('class','backward');
          //更新向右的按钮
          if(moveCount === imgCount - SHOW_COUNT){
              $forward.attr('class','forward_disabled');
          }
          //移动ul
          $ul.css({
              left:-moveCount*liWidth
          })
      });

        $backward.click(function () {
            //判断是否需要移动,如果不需要直接结束
            if(moveCount === 0){
                return;
            }
            moveCount--;
            //更新向右的按钮
            $forward.attr('class','forward');
            //更新向左的按钮
            if(moveCount === 0){
                $backward.attr('class','backward_disabled');
            }
            //移动ul
            $ul.css({
                left:-moveCount*liWidth
            })
        });
    }

    // 10. 当鼠标悬停在某个小图上,在上方显示对应的中图
    function hoverMiniImg() {
        $('#icon_list>li').hover(function () {
            var $img = $(this).children();
            // this.children()[0].className = 'hoverThumb';
            $img.addClass('hoveredThumb');
            //显示对应的中图
            var src = $img.attr('src').replace('.jpg','-m.jpg');
            $('#mediumImg').attr('src',src);
        },function () {
            $(this).children().removeClass('hoveredThumb');
        });
    }

    // 11. 当鼠标在中图上移动时, 显示对应大图的附近部分区域
});