function y = hyp_tan_d(x, beta)
  y = beta .* (1.0 - hyp_tan(x, beta) .^ 2.0);
end