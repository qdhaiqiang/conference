/**
 * easyui datetimebox日期格式化工具类
 * @param selctor 要格式化的控件id
 */
function datetimebox(selctor,inputVal) {
	$('#'+selctor).datetimebox({
		showSeconds:false,
		onSelect:function(date){
			var time=$('#'+selctor).datetimebox('spinner').spinner('getValue');
			$('#'+selctor).datetimebox('setText',date.getFullYear()+'/'+ ((date.getMonth()+1)<10 ? ('0'+(date.getMonth()+1)) : (date.getMonth()+1))+'/'+((date.getDate())<10 ? ('0'+(date.getDate())) : (date.getDate()))+' '+time);
			$('#'+inputVal).val($('#'+selctor).datetimebox('getText'));
			//$('#'+selctor).datetimebox('setValue',$('#'+selctor).datetimebox('getText'));
			$('#'+selctor).datetimebox('hidePanel');
		}
	});
}