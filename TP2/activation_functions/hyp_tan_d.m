function y = hyp_tan_d(x, beta)
  y = beta .* (1.0 - tanh(x).^2.0);
end