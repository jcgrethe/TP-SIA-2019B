function x = sigmoid_exp_inv(y, beta)
  x = (-1 / (2 * beta)) * log(1/y - 1);
endfunction
