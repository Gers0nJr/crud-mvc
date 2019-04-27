$('#confirmacaoExclusaoModalCliente').on('shown.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var idCliente = button.data('id');
	var nomeCliente = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idCliente);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o cliente <strong>' + nomeCliente + '</strong>?');
	  
});

$('#confirmacaoExclusaoModalServico').on('shown.bs.modal', function(event) {
	
	var button = $(event.relatedTarget);
	
	var idServico = button.data('id');
	var nomeServico = button.data('nome');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + idServico);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o serviço <strong>' + nomeServico + '</strong>?');
	  
});

$(document).ready(function() {
    $('.js-example-basic-multiple').select2();
});

$(document).ready(function(){
    var date_input=$('input[name="dataNascimento"]'); //our date input has the name "date"
    var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
    var options={
      format: 'dd/mm/yyyy',
      container: container,
      todayHighlight: true,
      autoclose: true,
    };
    date_input.datepicker(options);
  });