function w = incremental_trainer(input_patterns)

  hidden_layers = [20 20 20 3];
  #Data init	
	v = {[-1 * ones(1, rows(input_patterns)); input_patterns(1:end,1:end - 1)']'};
  v{1} = v{1}';
  S = [input_patterns(:,end)];
	for i = 1:length(hidden_layers)
		v{i + 1} = [-1 * ones(1, rows(input_patterns)); zeros(rows(input_patterns), hidden_layers(i))']';
    v{i + 1} = v{i + 1}';
    d{i + 1} = zeros(hidden_layers(i), 1);
	endfor
  v{end + 1} = zeros(1, rows(input_patterns));
  d{end + 1} = zeros(1, 1);
  #Weights and deltas
    w = {rand(hidden_layers(1), columns(input_patterns)) - 0.5};
  dw = {zeros(hidden_layers(1), columns(input_patterns))};
  for i = 1:length(hidden_layers) - 1
      w{i + 1} = rand(hidden_layers(i + 1), hidden_layers(i) + 1) - 0.5;
  dw{i + 1} = zeros(hidden_layers(i + 1), hidden_layers(i) + 1);
  endfor
  w{end + 1} = rand(1, hidden_layers(end) + 1) - 0.5;
  dw{end + 1} = zeros(1, hidden_layers(end) + 1);
  epoch = 0;

  # ETAs config values
  eta = 0.01;
  eta_a = 0.0001;
  eta_b = 0.05;
  eta_min = 0.0005;
  eta_max = 0.1;

  MAX_ERROR = 0.01;
  global_q_error = 1;
  total_error = 1;
  last_error = 99999999;

  while(epoch < 1500)
    global_q_error = 0;

    #printf("Epoch %d\n",epoch);

    indexes = randperm(rows(input_patterns));
    for k = 1:length(indexes)
      p = indexes(k);
      for i = 1:length(hidden_layers)
         v{i + 1}(2:end, p) = hyp_tan((w{i} * v{i}(:,p)));
      endfor
      v{end}(:, p) = hyp_tan((w{end} * v{end-1}(:,p)));

      
      #printf("[%d %d] = %d | %d\n",input_patterns(p,1), input_patterns(p,2),v{end}(:,p),S(p));
      
      
      d{end} = hyp_tan_d(w{end} * v{end-1}(:,p)) .* (S'(:,p) - v{end}(:,p));
      for i = 1:length(d) - 2
        aux = w{end-i} * v{end - i - 1}(:,p);
        d{end - i} = hyp_tan_d(aux) .* (w{end - i + 1}(:,2:end)' * d{end - i + 1});
      endfor
      
      for i = 1:length(w) 
        dw{i} = eta * d{i + 1} * v{i}(:,p)';
        w{i} = w{i} + dw{i};
      endfor
    endfor
    total_error = 0;
     for i = 1:rows(input_patterns)
        partial_dif = S(i) - v{end}(i);
        partial_error = (1 / (2 * rows(input_patterns))) * (partial_dif)**2;
        total_error += partial_error;
     endfor

    # Update ETA
    delta_error = total_error - last_error
    last_error = total_error;
    delta_eta = 0; 
    if delta_error < 0
      delta_eta = eta_a;
    elseif delta_error > 0
      delta_eta = -1 * eta_b * eta;
    end
    eta += delta_eta  

    figure(2);
    xlabel ("Epoch");
    ylabel ("ECM");
    plot(epoch, total_error, '.-');
    hold on;

    figure(5);
    xlabel ("Epoch");
    ylabel ("ETA");
    plot(epoch, eta, '.-');
    hold on;

    epoch++;
  end
  
    #xlabel ("Epoch");
    #ylabel ("ECM");
    #plot(epoch, total_error);
    #hold on;
endfunction
