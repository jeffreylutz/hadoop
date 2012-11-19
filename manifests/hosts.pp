host { 'master.big.com':
    ip => '192.168.56.10',
    host_aliases => 'master',
}
host { 'node1.big.com':
    ip => '192.168.56.11',
    host_aliases => 'node1',
}
host { 'node2.big.com':
    ip => '192.168.56.12',
    host_aliases => 'node2',
}
host { 'node3.big.com':
    ip => '192.168.56.13',
    host_aliases => 'node3',
}
host { 'node4.big.com':
    ip => '192.168.56.14',
    host_aliases => 'node4',
}

user { 'root':
  ensure     => 'present',
  password => '21232F297A57A5A743894A0E4A801FC3'
}