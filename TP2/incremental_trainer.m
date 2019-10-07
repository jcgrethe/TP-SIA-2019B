function [w, total_patterns] = incremental_trainer(input_patterns)

  configuration;
  data_init;

  while(epoch < MAX_EPOCH && total_error > MAX_ERROR)

    #printf("Epoch %d\n",epoch);

    indexes = randperm(rows(input_patterns));
    for k = 1:length(indexes)
      p = indexes(k);
      for i = 1:length(hidden_layers)
         v{i + 1}(2:end, p) = func((w{i} * v{i}(:,p)), beta);
      endfor
      v{end}(:, p) = func((w{end} * v{end-1}(:,p)), beta);

      #printf("[%d %d] = %d | %d\n",input_patterns(p,1), input_patterns(p,2),v{end}(:,p),S(p));      
      
      d{end} = func_d(w{end} * v{end-1}(:,p), beta) .* (S'(:,p) - v{end}(:,p));
      for i = 1:length(d) - 2
        aux = w{end-i} * v{end - i - 1}(:,p);
        d{end - i} = func_d(aux, beta) .* (w{end - i + 1}(:,2:end)' * d{end - i + 1});
      endfor
      
      for i = 1:length(w) 

        # Momentum
        if (momentum)
          momentum_update = momentum_value * last_dw{i};
        else
          momentum_update = 0;
        endif

        dw{i} = eta * d{i + 1} * v{i}(:,p)' + momentum_update;
        w{i} = w{i} + dw{i};
      endfor

      last_dw = dw;
    endfor
    
    total_error = 0;
    for i = 1:rows(input_patterns)
      partial_dif = S(i) - v{end}(i);
      partial_error = (1 / (2 * rows(input_patterns))) * (partial_dif)**2;
      total_error += partial_error;
    endfor

    # Update ETA
    if(adaptative_eta)
      delta_error = total_error - last_error;
      last_error = total_error;
      delta_eta = 0; 
      if delta_error < 0
        delta_eta = eta_a;
      elseif delta_error > 0
        delta_eta = -1 * eta_b * eta;
      end
      eta = eta + delta_eta;
      
      if(eta > eta_max)
        eta = eta_max;
      endif

      if(eta < eta_min)
        eta = eta_min;
      endif

      figure(5);
      title('ETA en funcion de la época');
      xlabel ("Epoch");
      ylabel ("ETA");
      plot(epoch, eta, '.-');
      hold on;
    endif

    figure(2);
    title('Error en función de la época');
    xlabel ("Epoch");
    ylabel ("ECM");
    plot(epoch, total_error, '.-');
    hold on;

    epoch++;
  end
  
endfunction
