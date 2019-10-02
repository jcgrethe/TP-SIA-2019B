addpath("./activation_functions");

#Just one output

function [w] = incremental_trainer(dataset, percentage, hidden_layers)

	#Get X% of random normalized patterns from dataset
	#input_patterns = get_random_patterns(dataset, percentage);
  input_patterns = [0,0,0;1,0,1;0,1,1;1,1,0];

  #Data init	
	v = {[-1 * ones(1, rows(input_patterns)); input_patterns(1:end,1:end - 1)']'};
  o = [input_patterns(1:end,end)];
	for i = 1:length(hidden_layers)
		v{i + 1} = [-1 * ones(1, rows(input_patterns)); zeros(rows(input_patterns), hidden_layers(i))']';
    d{i + 1} = zeros(rows(input_patterns), hidden_layers(i));
	endfor
  v{end + 1} = zeros(rows(input_patterns), 1);
  d{end + 1} = zeros(rows(input_patterns), 1);
 
  #Weights and deltas
  w = {rand(hidden_layers(1), columns(input_patterns)) .* 1/sqrt(columns(input_patterns) - 1) - 0.5};
  dw = {zeros(hidden_layers(1), columns(input_patterns))};
  for i = 1:length(hidden_layers) - 1
    w{i + 1} = rand(hidden_layers(i + 1), hidden_layers(i) + 1) .* 1/sqrt(hidden_layers(i)) - 0.5;
    dw{i + 1} = zeros(hidden_layers(i + 1), hidden_layers(i) + 1);
  endfor
  w{end + 1} = rand(1, hidden_layers(end) + 1) .* 1/sqrt(hidden_layers(end)) - 0.5;
  dw{end + 1} = zeros(1, hidden_layers(end) + 1);


  v
  d
  w
  dw

  epoch = 0;
  eta = 0.05;
  MAX_ERROR = 0.01;
  global_q_error = 1;


  while(global_q_error > MAX_ERROR)

    global_q_error = 0;
    #printf("Epoch %d\n",epoch);

    #TODO: Shuffle patterns taking random p;
    indexes = randperm(rows(input_patterns));

    for k = 1:length(indexes)

      p = indexes(k);

      for i = 1:length(hidden_layers) + 1
         v{i + 1}(p,2:end) = sigmoid_exp((w{i} * v{i}(p,:)')');
      endfor
      v{end}(p,:) = sigmoid_exp((w{end} * v{end-1}(p,:)')');

      final_dif = o(p) - v{end}(p,:);
      
      printf("%d/%d\n",v{end}(p,:),o(p));

      d{end}(p,:) = sigmoid_exp_d(v{end}(p,:)) .* final_dif;
      for i = 1:length(d) - 2
        d{end - i}(p,:) = sigmoid_exp_d(v{end - i + 1}(p,:)) .* (w{end - i + 1}(2:end)' * d{end - i + 1}(p,:));
      endfor
      
      for i = 1:length(w) 
        dw{i} = eta * d{i + 1}(p,:)' * v{i}(p,:);
        w{i} = w{i} + dw{i};
      endfor

      q_error = (1 / (2 * rows(input_patterns))) * (final_dif)**2;
      global_q_error += q_error;

    endfor

    #xlabel ("Epoch");
    #ylabel ("ECM");
    #plot(epoch, global_q_error);
    #hold on;

    epoch++;
  end


endfunction

incremental_trainer("terrain02.data",40, [2]);