function trim(sText)
	{ 
		return sText.replace(new RegExp("(^[\\s]*)|([\\s]*$)", "g"), ""); 
	}
function toConfirm(msg, url) {
		if (confirm(msg)) {
			top.window.location.href = url;
		}
}

function openUrl(url){
	top.window.location.href = url;
}
function toConfirm1(msg) {
	return confirm(msg);
}

//判断字符串为NUll或空白字符组成的串
function isNullOrBlank(str)
{
	var regex = /^\s+$/g;
	if(str.length==0 || regex.test(str))
	{
		return  true;
	}
	else
	{
		return false;
	}
}


//----------------start:笔记的分类管理---------------------//
function checkCategory()
{
	var category = document.getElementById("newCategory");
	if(isNullOrBlank(category.value))
	{
		alert("请输入分类名称");
		return;
	}
	else if(category.value == '默认分类')
	{
		alert('此分类不可添加');
		return;
	}
	var tabNode = document.getElementById("mytable");
	var trNode = tabNode.insertRow(-1);
	
	var tdNode1 = trNode.insertCell(-1);
	//因为数据库中默认是自增id是从1开始的，新增的赋予id=0（不知可不可）
	tdNode1.innerHTML = "<input type='hidden' name='idCustom' value='0'>"+"<input type='hidden' name='customName' value='"+category.value+"'>"+category.value;
	category.value="";
	tdNode1.className = "td_left";
	
	var tdNode2 = trNode.insertCell(-1);
	tdNode2.innerHTML = "<input type='hidden' name='noteAmount' value='0'>"+0;
	tdNode2.className = "td_center";
	
	var tdNode3 = trNode.insertCell(-1);
	tdNode3.innerHTML = "<a href='javascript:void(0);' onclick='editRow(this);'>编辑</a>|"+"<a href='javascript:void(0);' onclick='delRow(this)'>删除</a>|显示|隐藏";
	tdNode3.className = "td_right";
	
}

function delRow(aNode)
{
	if(!confirm('确定删除此分类吗？删除后，此分类下的笔记将转移到默认分类下'))
	{
		return;
	}
	var tabNode = document.getElementById("mytable");
	var trNode = aNode.parentNode.parentNode;
	var index = trNode.rowIndex;
	
	//1.删除分类时，将删除分类下的笔记加到默认分类的笔记数目上
	var defTdNode = tabNode.rows[2].cells[1];
	//-----start:处理浏览器对于innerText和textContent的兼容问题（IE:innerText可用，FF:textContent可用）----------//
	var defText = document.all?defTdNode.innerText:defTdNode.textContent;
	var trNodeText = document.all?trNode.cells[1].innerText:trNode.cells[1].textContent;
	//-----end:处理浏览器对于innerText和textContent的兼容问题（IE:innerText可用，FF:textContent可用）----------//
	var noteAmount = parseInt(defText)+parseInt(trNodeText);
	defTdNode.innerHTML = "<input type='hidden' name='noteAmount' value='"+noteAmount+"'>"+noteAmount;
	//2.删除分类时，将删除分类的id添加到deletedCategory串上
	var deletedCategory = document.getElementById("deletedCategory");
	
	deletedCategory.value = deletedCategory.value + trNode.cells[0].children[0].value+",";

	tabNode.deleteRow(index);
}

function editRow(aNode)
{
	var tabNode = document.getElementById("mytable");
	var trNode = aNode.parentNode.parentNode;
	
	var tdNode = trNode.cells[0];

	var idCustom = tdNode.children[0].value; 
	var category = tdNode.children[1].value;
	
	tdNode.innerHTML = "<input type='hidden' name='idCustom' value='"+idCustom+"'>"+"<input type='hidden' name='customName' value='"+category.value+"'>"+"<input type='text' size='15' name="+category+" value="+category+">"+"<a href='javascript:void(0);' onclick='storeCategroy(this)'>保存</a>"+"<input type='hidden' value="+category+">"+"<a href='javascript:void(0);' onclick='cancelModify(this)'>取消</a>";
	aNode.detachEvent ('onclick', editRow);
}

function storeCategroy(aNode)
{
	var tdNode = aNode.parentNode;
	var inputNode = aNode.previousSibling;
	var idCustom = tdNode.children[0].value;
	tdNode.innerHTML = "<input type='hidden' name='idCustom' value='"+idCustom+"'>"+"<input type='hidden' name='customName' value='"+inputNode.value+"'>"+inputNode.value;
	//保存修改时，将被修改的分类的id和新名称保存起来(不管其是否是新增的分类，或者改过后又被删除了)
	var modifiedId = document.getElementById("modifiedId");
	modifiedId.value = modifiedId.value + idCustom+",";
	
	var modifiedName = document.getElementById("modifiedName");
	modifiedName.value = modifiedName.value + inputNode.value+",";
	
}

function cancelModify(aNode)
{
	var tdNode = aNode.parentNode;
	var idCustom = tdNode.children[0].value;
	var oldcategory = aNode.previousSibling.value;
	tdNode.innerHTML = "<input type='hidden' name='idCustom' value='"+idCustom+"'>"+"<input type='hidden' name='customName' value='"+oldcategory+"'>"+oldcategory;
}
//----------------end:笔记的分类管理---------------------//
//--------------------发帖--------------------------------//
/**
 * 点击回复的时候动态添加文本框
 */
function createReplyBox(formid,alinkid) {
	var form=document.getElementById(formid);
	var alinkNode=document.getElementById(alinkid);
	if(alinkNode.innerHTML=="回复"){
		form.style.display="inline";
		alinkNode.innerHTML="收起回复";
	}else if(alinkNode.innerHTML=="收起回复"){
		form.style.display="none";
		alinkNode.innerHTML="回复";
	}
}


