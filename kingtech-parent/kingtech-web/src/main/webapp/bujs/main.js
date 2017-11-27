function menuChecked(menu){
	var $menu = $(menu);
	$menu.closest(".treeview-menu").show();
	$menu.find("a").css("font-weight",700).addClass("text-green"); 
} 
